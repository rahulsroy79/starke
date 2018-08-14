/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { Starkev2TestModule } from '../../../test.module';
import { StarkeUsersMySuffixUpdateComponent } from 'app/entities/starke-users-my-suffix/starke-users-my-suffix-update.component';
import { StarkeUsersMySuffixService } from 'app/entities/starke-users-my-suffix/starke-users-my-suffix.service';
import { StarkeUsersMySuffix } from 'app/shared/model/starke-users-my-suffix.model';

describe('Component Tests', () => {
    describe('StarkeUsersMySuffix Management Update Component', () => {
        let comp: StarkeUsersMySuffixUpdateComponent;
        let fixture: ComponentFixture<StarkeUsersMySuffixUpdateComponent>;
        let service: StarkeUsersMySuffixService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Starkev2TestModule],
                declarations: [StarkeUsersMySuffixUpdateComponent]
            })
                .overrideTemplate(StarkeUsersMySuffixUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(StarkeUsersMySuffixUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(StarkeUsersMySuffixService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new StarkeUsersMySuffix(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.starkeUsers = entity;
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
                    const entity = new StarkeUsersMySuffix();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.starkeUsers = entity;
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
