/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { Starkev2TestModule } from '../../../test.module';
import { EntityReviewsMySuffixUpdateComponent } from 'app/entities/entity-reviews-my-suffix/entity-reviews-my-suffix-update.component';
import { EntityReviewsMySuffixService } from 'app/entities/entity-reviews-my-suffix/entity-reviews-my-suffix.service';
import { EntityReviewsMySuffix } from 'app/shared/model/entity-reviews-my-suffix.model';

describe('Component Tests', () => {
    describe('EntityReviewsMySuffix Management Update Component', () => {
        let comp: EntityReviewsMySuffixUpdateComponent;
        let fixture: ComponentFixture<EntityReviewsMySuffixUpdateComponent>;
        let service: EntityReviewsMySuffixService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Starkev2TestModule],
                declarations: [EntityReviewsMySuffixUpdateComponent]
            })
                .overrideTemplate(EntityReviewsMySuffixUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(EntityReviewsMySuffixUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EntityReviewsMySuffixService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new EntityReviewsMySuffix(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.entityReviews = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new EntityReviewsMySuffix();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.entityReviews = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
