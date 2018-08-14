/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { Starkev2TestModule } from '../../../test.module';
import { EntityPageFormatsMySuffixUpdateComponent } from 'app/entities/entity-page-formats-my-suffix/entity-page-formats-my-suffix-update.component';
import { EntityPageFormatsMySuffixService } from 'app/entities/entity-page-formats-my-suffix/entity-page-formats-my-suffix.service';
import { EntityPageFormatsMySuffix } from 'app/shared/model/entity-page-formats-my-suffix.model';

describe('Component Tests', () => {
    describe('EntityPageFormatsMySuffix Management Update Component', () => {
        let comp: EntityPageFormatsMySuffixUpdateComponent;
        let fixture: ComponentFixture<EntityPageFormatsMySuffixUpdateComponent>;
        let service: EntityPageFormatsMySuffixService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Starkev2TestModule],
                declarations: [EntityPageFormatsMySuffixUpdateComponent]
            })
                .overrideTemplate(EntityPageFormatsMySuffixUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(EntityPageFormatsMySuffixUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EntityPageFormatsMySuffixService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new EntityPageFormatsMySuffix(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.entityPageFormats = entity;
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
                    const entity = new EntityPageFormatsMySuffix();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.entityPageFormats = entity;
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
